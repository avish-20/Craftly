package com.avish.Craftly.service.impl;

import com.avish.Craftly.dto.project.FileContentResponse;
import com.avish.Craftly.dto.project.FileNode;
import com.avish.Craftly.service.FIleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FIleService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, String path, Long userId) {
        return null;
    }
}
