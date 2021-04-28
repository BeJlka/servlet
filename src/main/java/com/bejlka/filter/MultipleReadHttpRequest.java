package com.bejlka.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class MultipleReadHttpRequest extends HttpServletRequestWrapper {
    private final ByteArrayOutputStream cachedContent = new ByteArrayOutputStream();

    public MultipleReadHttpRequest(HttpServletRequest request) throws IOException {
        super(request);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = super.getInputStream().read(buffer)) != -1) {
            cachedContent.write(buffer, 0, len);
        }
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(cachedContent.toByteArray());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return inputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public int read() {
                return inputStream.read();
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                throw new UnsupportedOperationException();
            }
        };
    }
}