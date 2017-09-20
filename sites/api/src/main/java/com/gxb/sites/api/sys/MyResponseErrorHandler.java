package com.gxb.sites.api.sys;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by ${sunninghai} on 16-1-11.
 */
public class MyResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return hasError(getHttpStatusCode(response));
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        int statusCode = getHttpStatusCode(response);
        int seriesCode = statusCode / 100;
        switch (seriesCode) {
            case 4:
                throw new HttpClientErrorException(response.getStatusCode(), response.getStatusText(),
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            case 5:
                throw new HttpServerErrorException(response.getStatusCode(), response.getStatusText(),
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            case 10:
                throw new HttpServiceErrorException(statusCode, null,
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            default:
                throw new RestClientException("Unknown status code [" + statusCode + "]");
        }
    }

    private int getHttpStatusCode(ClientHttpResponse response) throws IOException {
        return response.getRawStatusCode();
    }

    protected boolean hasError(int statusCode) {
        int seriesCode = statusCode / 100;
        return seriesCode==4 || seriesCode==5 || seriesCode==10;

    }

    private byte[] getResponseBody(ClientHttpResponse response) {
        try {
            InputStream responseBody = response.getBody();
            if (responseBody != null) {
                return FileCopyUtils.copyToByteArray(responseBody);
            }
        }
        catch (IOException ex) {
            // ignore
        }
        return new byte[0];
    }

    private Charset getCharset(ClientHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        MediaType contentType = headers.getContentType();
        return contentType != null ? contentType.getCharSet() : null;
    }
}

