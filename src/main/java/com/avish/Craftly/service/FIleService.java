package com.avish.Craftly.service;

import com.avish.Craftly.dto.project.FileContentResponse;
import com.avish.Craftly.dto.project.FileNode;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface FIleService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}
