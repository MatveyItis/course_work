package ru.itis.teamwork.services.modelgit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommitFile {

    @JsonProperty("file_name")
    private String fileName;
    @JsonProperty("status")
    private String status;
    @JsonProperty("contents_url")
    private String contentsUrl;
}