package com.cristianl.service.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test different methods of versioning. </br>
 * 1. URI version, /v1/hello, /v2/hello </br>
 * 2. PARAM version, /hello?version=1, /hello?version=2 </br>
 * 3. HEADER version, API-VERSION: 1, API-VERSION: 2 </br>
 * 4. MEDIA_TYPE version, ACCEPT: application/v1+json, ACCEPT:
 * application/v2+json </br>
 */
@RestController
public class HelloResource {

	/* URI version */
	@GetMapping(path = "v1/hello", produces = "application/text")
	public String uri_version1() {
		return "Hello user, you are using version 1";
	}

	/* URI version */
	@GetMapping(path = "v2/hello", produces = "application/text")
	public String uri_version2() {
		return "Hello user, you are using version 2";
	}

	/* PARAM version */
	@GetMapping(value = "hello", params = "version=1", produces = "application/text")
	public String param_version1() {
		return "Hello user, you are using version 1";
	}

	/* PARAM version */
	@GetMapping(value = "hello", params = "version=2", produces = "application/text")
	public String param_version2() {
		return "Hello user, you are using version 2";
	}

	/* HEADER version */
	@GetMapping(value = "hello", headers = "API-VERSION=1", produces = "application/text")
	public String header_version1() {
		return "Hello user, you are using version 1";
	}

	/* HEADER version */
	@GetMapping(value = "hello", headers = "API-VERSION=2", produces = "application/text")
	public String header_version2() {
		return "Hello user, you are using version 2";
	}

	/* MEDIA_TYPE version */
	@GetMapping(value = "hello", produces = "application/v1+text")
	public String mediaType_version1() {
		return "Hello user, you are using version 1";
	}

	/* MEDIA_TYPE version */
	@GetMapping(value = "hello", produces = "application/v2+text")
	public String mediaType_version2() {
		return "Hello user, you are using version 2";
	}
}
