package interview.infrastructure.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.extractor.EmbeddedDocumentExtractor;
import org.apache.tika.metadata.Metadata;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

/**
 * 空操作的嵌入文档提取器
 * 用于禁用 Tika 对嵌入资源（图片、附件等）的解析
 *
 */
@Slf4j
public class NoOpEmbeddedDocumentExtractor implements EmbeddedDocumentExtractor {

    @Override
    public void parseEmbedded(InputStream inputStream, ContentHandler contentHandler, Metadata metadata, boolean b) throws SAXException, IOException {
        //shouldParseEmbedded始终返回false，因此不做任何操作
    }

    /**
     * 是否应该解析嵌入文档
     * @param metadata 文档元数据
     * @return 始终返回 false，禁用嵌入文档解析
     */
    @Override
    public boolean shouldParseEmbedded(Metadata metadata) {
        // 记录跳过的嵌入文档（使用字符串常量，兼容不同 Tika 版本）
        String resourceName = metadata.get("resourceName");
        if (resourceName != null) {
            log.debug("resourceName: {}", resourceName);
        }
        return false;
    }
}
