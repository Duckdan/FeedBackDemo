package study.com.feedbackdemo.imagepicker;

import study.com.feedbackdemo.imagepicker.util.StorageUtil;

/**
 * 存储的类型
 */
public enum StorageType {
    /**
     * 日志文件存储类型
     */
    TYPE_LOG(DirectoryName.LOG_DIRECTORY_NAME),
    /**
     * 临时文件存储类型
     */
    TYPE_TEMP(DirectoryName.TEMP_DIRECTORY_NAME),
    /**
     * 文件存储类型
     */
    TYPE_FILE(DirectoryName.FILE_DIRECTORY_NAME),
    /**
     * 音频文件存储类型
     */
    TYPE_AUDIO(DirectoryName.AUDIO_DIRECTORY_NAME),
    /**
     * 图片文件存储类型
     */
    TYPE_IMAGE(DirectoryName.IMAGE_DIRECTORY_NAME),
    /**
     * 视频文件存储类型
     */
    TYPE_VIDEO(DirectoryName.VIDEO_DIRECTORY_NAME),
    TYPE_THUMB_IMAGE(DirectoryName.THUMB_DIRECTORY_NAME),
    TYPE_THUMB_VIDEO(DirectoryName.THUMB_DIRECTORY_NAME),;
    private DirectoryName storageDirectoryName;
    private long storageMinSize;

    public String getStoragePath() {
        return storageDirectoryName.getPath();
    }

    public long getStorageMinSize() {
        return storageMinSize;
    }

    StorageType(DirectoryName dirName) {
        this(dirName, StorageUtil.THRESHOLD_MIN_SPCAE);
    }

    StorageType(DirectoryName dirName, long storageMinSize) {
        this.storageDirectoryName = dirName;
        this.storageMinSize = storageMinSize;
    }

    enum DirectoryName {
        AUDIO_DIRECTORY_NAME("audio/"),
        DATA_DIRECTORY_NAME("data/"),
        FILE_DIRECTORY_NAME("file/"),
        LOG_DIRECTORY_NAME("log/"),
        TEMP_DIRECTORY_NAME("temp/"),
        IMAGE_DIRECTORY_NAME("image/"),
        THUMB_DIRECTORY_NAME("thumb/"),
        VIDEO_DIRECTORY_NAME("video/"),;

        private String path;

        public String getPath() {
            return path;
        }

        private DirectoryName(String path) {
            this.path = path;
        }
    }
}
