package me.kzv.minio.service

import io.minio.BucketExistsArgs
import io.minio.GetObjectArgs
import io.minio.MakeBucketArgs
import io.minio.MinioClient
import io.minio.PutObjectArgs
import io.minio.RemoveObjectArgs
import org.springframework.stereotype.Service
import java.io.InputStream


@Service
class StorageService(
    private val minioClient: MinioClient
) {
    fun putObject(bucketName: String, objectName: String, inputStream: InputStream, contentType: String? = null) {
        try {
            if (!isBucketExist(bucketName)) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build())
            }

            val putObjectArgs = PutObjectArgs.builder().bucket(bucketName).`object`(objectName)
                .stream(inputStream, inputStream.available().toLong(), -1)
                .contentType(contentType)
                .build()

            minioClient.putObject(putObjectArgs)
        } catch (e: Exception) {
            throw RuntimeException("Error occurred: " + e.message)
        }
    }

    fun removeObject(bucketName: String, objectName: String) {
        try {
            if (isBucketExist(bucketName)) {
                val removeObjectArgs = RemoveObjectArgs.builder().bucket(bucketName).`object`(objectName).build()
                minioClient.removeObject(removeObjectArgs)
            }
        } catch (e: Exception) {
            throw RuntimeException("Error occurred: " + e.message)
        }
    }

    fun getObject(bucketName: String, objectName: String): ByteArray {
        return if (isBucketExist(bucketName)) {
            try {
                val getObjectArgs = GetObjectArgs.builder().bucket(bucketName).`object`(objectName).build()
                minioClient.getObject(getObjectArgs).readBytes()
            } catch (e: Exception) {
                throw RuntimeException("Error occurred: " + e.message)
            }
        } else {
            throw RuntimeException("Error occurred: ")
        }
    }

    private fun isBucketExist(bucketName: String) = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())
}