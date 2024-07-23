package com.comrade.basics.mono.assignment;

import com.comrade.util.Util;

public class FileOperationsClient {

    public static void main(String[] args) {
        FileOperations.monoReadData("read1.txt").subscribe(Util.onNext, Util.onError, Util.onComplete);
        FileOperations.monoWriteData("write.txt", "hey whats going on").subscribe(Util.onNext, Util.onError, Util.onComplete);
        FileOperations.monoDeleteFile("write.txt").subscribe(Util.onNext, Util.onError, Util.onComplete);
    }

}
