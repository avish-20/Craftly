package com.avish.Craftly.controller;

import com.avish.Craftly.dto.project.FileContentResponse;
import com.avish.Craftly.dto.project.FileNode;
import com.avish.Craftly.service.FIleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects/{projectId}/files")
public class FileController {
    private final FIleService fileService;

    @GetMapping
    public ResponseEntity<List<FileNode>> getFile(@PathVariable Long projectId){
        Long userId = 1L;
        return ResponseEntity.ok(fileService.getFileTree(projectId,userId));

    }

    @GetMapping("/{*path}") //src/hooks/AppHook.jsx
    public ResponseEntity<FileContentResponse> getFile(@PathVariable Long projectId,
                                                       @PathVariable String path){
        Long userId = 1L;
        return ResponseEntity.ok(fileService.getFileContent(projectId, path, userId));
    }

}
