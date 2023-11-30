package com.holme.be_app.entity

import com.holme.be_app.dto.ReportDto
import jakarta.persistence.*
import java.util.Date

public enum class ReportType { SYNC, UPGRADE, REPLACEMENT }

@Entity
class Report (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column(nullable = false)
    val payload: String,

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    val reportType: ReportType,

    @Column(nullable = false)
    val checked: Boolean = false, //* Default Value = false

    @Column(nullable = true)
    val created_at: String? ="",

    @ManyToOne
    @JoinColumn(name = "userId", unique = false)
    val user:ServiceUser
)

fun Report.toDto(): ReportDto {
    return ReportDto(
        this.id,
        this.payload,
        this.reportType,
        this.checked,
        this.user.id,
        this.created_at,
    )
}

