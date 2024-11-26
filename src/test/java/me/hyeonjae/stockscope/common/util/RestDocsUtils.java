package me.hyeonjae.stockscope.common.util;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

public interface RestDocsUtils {
    
    static OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(
                modifyUris()
                    .scheme("https")
                    .host("api.stockscope.com")
                    .removePort(),
                prettyPrint()
        );
    }

    static OperationResponsePreprocessor getDocumentResponse() {
        return preprocessResponse(prettyPrint());
    }
}
