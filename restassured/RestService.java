package service.restassured;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public abstract class RestService {
    public Map<String, String> headers() {
        return null;
    }

    public Response sendGetRequest(String baseUri, String basePath) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;

        RequestSpecification request = RestAssured.given();
        request.headers(headers());
        request.urlEncodingEnabled(false);
        // request.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return request.get();
    }

    public Response sendGetRequest(String baseUri, String basePath, Map<String, String> params) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;

        RequestSpecification request = RestAssured.given();
        request.params(params);
        request.headers(headers());
//        request.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return request.get();
    }

    public Response sendPostRequest(String baseUri, String basePath, Object body) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;

        RequestSpecification request = RestAssured.given();
        request.contentType("application/json");
        request.headers(headers());
        request.body(body);
        // request.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return request.post();
    }

    public Response sendPutRequest(String baseUri, String basePath, Object body) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;

        RequestSpecification request = RestAssured.given();
        request.contentType("application/json");
        request.headers(headers());
        request.body(body);
        // request.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return request.put();
    }

    public Response sendPutRequest(String baseUri, String basePath) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;

        RequestSpecification request = RestAssured.given();
        request.contentType("application/json");
        request.headers(headers());
        // request.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return request.put();
    }

    public Response sendPostRequest(String baseUri, String basePath, Map<String, String> params, Object body) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;

        RequestSpecification request = RestAssured.given();
        request.contentType("application/json");
        request.headers(headers());
        request.params(params);
        request.body(body);
//        request.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return request.post();
    }

    public Response sendDeleteRequest(String baseUri, String basePath) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;

        RequestSpecification request = RestAssured.given();

        request.headers(headers());
//        request.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return request.delete();
    }

    public Response sendDeleteRequest(String baseUri, String basePath, Map<String, String> params) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;

        RequestSpecification request = RestAssured.given();

        request.params(params);
        request.headers(headers());
//        request.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return request.delete();
    }

    public Response sendDeleteRequest(String baseUri, String basePath, Map<String, String> params, Object body) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;

        RequestSpecification request = RestAssured.given();

        request.params(params);
        request.headers(headers());
        request.body(body);
//        request.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return request.delete();
    }

    public Response sendDeleteRequest(String baseUri, String basePath, Object body) {
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = basePath;

        RequestSpecification request = RestAssured.given();

        request.headers(headers());
        request.body(body);
//        request.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return request.delete();
    }
}
