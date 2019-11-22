package utils.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class RestAssuredLogger {
    private PrintStream myPrintStream;

    /**
     * @return printStream
     */
    public PrintStream getPrintStream() {
        if (myPrintStream == null) {
            OutputStream output = new OutputStream() {
                private StringBuilder myStringBuilder = new StringBuilder();

                @Override
                public void write(int b) throws IOException {
                    this.myStringBuilder.append((char) b);
                }

                /**
                 * @see java.io.OutputStream#flush()
                 */
                @Override
                public void flush() {
                    String msg = this.myStringBuilder.toString();
                    if (!msg.isEmpty() && !msg.equals("\r\n")) {
                        LoggerSupplier.getLogger().info(msg);
                    }
                    myStringBuilder = new StringBuilder();
                }
            };

            // Second argument is true: autoflush must be set!
            myPrintStream = new PrintStream(output, true);
        }

        return myPrintStream;
    }
}
