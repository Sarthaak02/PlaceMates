package com.placemates.dao.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "BATCH")
@Data
@AllArgsConstructor
public class BatchDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BAT_ID")
    private Integer batchId;

    @Column(name = "BATCH_YEAR")
    private Integer batchYear;
}
