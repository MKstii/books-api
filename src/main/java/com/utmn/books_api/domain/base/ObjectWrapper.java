package com.utmn.books_api.domain.base;

import lombok.Data;

/**
 * Обертка для объектов, где передается id какого-то объекта, чтобы маппер создал нужного
 */
@Data
public class ObjectWrapper {

    Long id;
}
