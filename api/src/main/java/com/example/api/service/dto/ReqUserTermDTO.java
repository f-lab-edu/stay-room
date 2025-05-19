package com.example.api.service.dto;

import com.example.base.enums.TermType;

public record ReqUserTermDTO(TermType termTypeId, Long version, boolean agreed) {

}
