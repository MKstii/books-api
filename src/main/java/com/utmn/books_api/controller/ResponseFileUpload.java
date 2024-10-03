package com.utmn.books_api.controller;

import lombok.Data;

@Data
public class ResponseFileUpload {
//    {
//        "operation_id": "string",
//            "href": "string",
//            "method": "string",
//            "templated": true
//    }

    private String operationId;
    private String href;
    private String method;
    private String templated;

}
