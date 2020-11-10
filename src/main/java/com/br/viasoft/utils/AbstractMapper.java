package com.br.viasoft.utils;


import java.util.List;
import java.util.stream.Collectors;

public interface AbstractMapper<FROM, TO> {

    TO map(FROM object);

    default List<TO> map(List<FROM> list) {
        if ( null == list )
            return null;

        return list.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }


}
