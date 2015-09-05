/**
 * Copyright (c) 2015 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jmnarloch.spring.cloud.feign;

/**
 * Lists all constants used by this component.
 *
 * @author Jakub Narloch
 */
public interface HttpEncoding {

    /**
     * The HTTP Accept-Encoding header.
     */
    String ACCEPT_ENCODING_HEADER = "Accept-Encoding";

    /**
     * The HTTP Content-Encoding header.
     */
    String CONTENT_ENCODING_HEADER = "Content-Encoding";

    /**
     * The GZIP encoding.
     */
    String GZIP_ENCODING = "gzip";

    /**
     * The Deflate encoding.
     */
    String DEFLATE_ENCODING = "deflate";
}
