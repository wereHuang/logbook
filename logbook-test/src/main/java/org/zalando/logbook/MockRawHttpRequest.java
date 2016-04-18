package org.zalando.logbook;

/*
 * #%L
 * Logbook: Test
 * %%
 * Copyright (C) 2015 Zalando SE
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ListMultimap;
import lombok.Builder;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static com.google.common.base.MoreObjects.firstNonNull;
import static org.zalando.logbook.Headers.firstNonNullNorEmpty;

public final class MockRawHttpRequest implements RawHttpRequest {

    private final Origin origin;
    private final String remote;
    private final String method;
    private final String requestUri;
    private final ListMultimap<String, String> headers;
    private final String contentType;
    private final Charset charset;

    @Builder
    public MockRawHttpRequest(@Nullable final Origin origin, 
            @Nullable final String remote,
            @Nullable final String method,
            @Nullable final String requestUri,
            @Nullable final ListMultimap<String, String> headers,
            @Nullable final String contentType,
            @Nullable final Charset charset) {
        this.origin = firstNonNull(origin, Origin.REMOTE);
        this.remote = firstNonNull(remote, "127.0.0.1");
        this.method = firstNonNull(method, "GET");
        this.requestUri = firstNonNull(requestUri, "http://localhost/");
        this.headers = firstNonNullNorEmpty(headers, ImmutableListMultimap.of());
        this.contentType = firstNonNull(contentType, "");
        this.charset = firstNonNull(charset, StandardCharsets.UTF_8);
    }

    @Override
    public String getRemote() {
        return remote;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getRequestUri() {
        return requestUri;
    }

    @Override
    public ListMultimap<String, String> getHeaders() {
        return headers;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public Origin getOrigin() {
        return origin;
    }

    @Override
    public HttpRequest withBody() throws IOException {
        return MockHttpRequest.builder()
                .headers(headers)
                .contentType(contentType)
                .charset(charset)
                .origin(origin)
                .remote(remote)
                .method(method)
                .requestUri(requestUri)
                .build();
    }

    public static RawHttpRequest create() {
        return builder().build();
    }

}
