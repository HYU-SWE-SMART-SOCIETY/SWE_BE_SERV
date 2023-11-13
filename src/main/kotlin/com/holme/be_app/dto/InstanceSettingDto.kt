package com.holme.be_app.dto

import com.holme.be_app.entity.InstanceSetting
import com.holme.be_app.repository.ServiceUserRepository

class InstanceSettingDto (
    val id: Int?,
    val settingName: String,
    val settingString: String,
    val userId: Int?
)

fun InstanceSettingDto.toEntity(
    serviceUserRepository: ServiceUserRepository
): InstanceSetting? {

    val foundUser = userId?.let { serviceUserRepository.findById(it).orElse(null) } ?: return null

    return InstanceSetting(
        this.id,
        this.settingName,
        this.settingString,
        user = foundUser,
    )
}