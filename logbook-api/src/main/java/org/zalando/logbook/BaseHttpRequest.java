package org.zalando.logbook;

/*
 * #%L
 * Logbook: API
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

public interface BaseHttpRequest extends BaseHttpMessage {

    String getRemote();

    String getMethod();

    // TODO(wschoenborn): remove query string
    // TODO(wschoenborn): add getter for query parameters
    // TODO(wschoenborn): render uri in formatters
    /**
     * Request URI including query string.
     *
     * <p>Note that the URI may be invalid if the client issued an HTTP request using a malformed URL.</p>
     *
     * @return  the requested URI
     */
    String getRequestUri();

}
