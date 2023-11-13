package com.holme.be_app.api.setting.upload.service

import com.holme.be_app.dto.InstanceSettingDto
import com.holme.be_app.dto.toEntity
import com.holme.be_app.repository.ServiceUserRepository
import com.holme.be_app.repository.SettingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UploadService (
    @Autowired val serviceUserRepository: ServiceUserRepository,
    @Autowired val settingRepository: SettingRepository
) {
    fun uploadSetting(userId: Int, settingId: Int?, setting: String): Boolean {
        return try{
            val instanceSetting = InstanceSettingDto(
                settingId, //* Overwrites if exists.
                settingString = setting,
                userId
            ).toEntity(serviceUserRepository) ?: throw Error("No Such User")

            settingRepository.save(instanceSetting)

            true
        }catch (e: Error){
            println("Error while uploading the setting: " + e.message)
            false
        }
    }
}