package interview.infrastructure.file;

import interview.common.exception.BusinessException;
import interview.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
public class FileHashService {
    /**
     * SHA-256:快速哈希算法
     */
    private static final String HASH_ALGORITHM = "SHA-256";
    /**
     * 8192bytes==8k
     * 计算机储存，内存，缓存常见容量单位
     * 平衡效率与内存占用
     */
    private static final int BUFFER_SIZE = 8192;

    /**
     * 计算文件的SHA-256哈希值
     */
    public String calculateHash(MultipartFile file) {
        try {
            return calculateHash(file.getBytes());
        } catch (IOException e) {
            log.error("读取文件内容失败: {}", e.getMessage());
            throw new BusinessException(ErrorCode.INTERNAL_ERROR, "计算文件哈希失败");
        }
    }

    /**
     * 计算字节数组的 SHA-256 哈希值
     *
     * @param data 字节数组
     * @return 十六进制哈希字符串
     */
    public String calculateHash(byte[] data) {
        try {
            //MessageDigest线程不安全，方法内部局部创建实例或使用ThreadLocal隔离线程实例
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hash = digest.digest(data);
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 流式计算文件的 SHA-256 哈希值（适用于大文件）
     *
     * @param inputStream 输入流
     * @return 十六进制哈希字符串
     */
    public String calculateHash(InputStream inputStream){
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead= inputStream.read(buffer))!=-1){
                digest.update(buffer,0,bytesRead);
            }
            return bytesToHex(digest.digest());
        }catch (NoSuchAlgorithmException | IOException e){
            throw new BusinessException(ErrorCode.INTERNAL_ERROR,"计算哈希失败");
        }
    }

    /**
     * 将字节数组转换为十六进制字符串
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
