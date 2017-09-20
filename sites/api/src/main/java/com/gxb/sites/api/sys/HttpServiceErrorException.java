package com.gxb.sites.api.sys;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Created by ${sunninghai} on 16-1-11.
 */
@Data
public class HttpServiceErrorException extends RestClientException {
    private static final long serialVersionUID = 4553196861717708534L;


    private static final String DEFAULT_CHARSET = "UTF-8";


    private final int statusCode;

    private final String statusText;

    private final byte[] responseBody;

    private final HttpHeaders responseHeaders;

    private final String responseCharset;



    protected HttpServiceErrorException(int statusCode) {
        this(statusCode, null, null, null);
    }


    protected HttpServiceErrorException(int statusCode, String statusText) {
        this(statusCode, statusText, null, null, null);
    }


    protected HttpServiceErrorException(
            int statusCode, String statusText, byte[] responseBody, Charset responseCharset) {

        this(statusCode, statusText, null, responseBody, responseCharset);
    }


    protected HttpServiceErrorException(int statusCode, String statusText,
                                        HttpHeaders responseHeaders, byte[] responseBody, Charset responseCharset) {

        super(statusCode + " " + statusText);
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody != null ? responseBody : new byte[0];
        this.responseCharset = responseCharset != null ? responseCharset.name() : DEFAULT_CHARSET;
    }




    /**
     * Return the response body as a string.
     * @since 3.0.5
     */
    public String getResponseBodyAsString() {
        try {
            return new String(this.responseBody, this.responseCharset);
        }
        catch (UnsupportedEncodingException ex) {
            // should not occur
            throw new IllegalStateException(ex);
        }
    }

}
