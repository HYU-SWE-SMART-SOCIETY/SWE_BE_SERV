package com.holme.be_app.api.setting.download.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.holme.be_app.api.setting.download.entity.DownloadPayload
import com.holme.be_app.repository.SettingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DownloadService (
    @Autowired val settingRepository: SettingRepository
) {
    fun downloadSettings(userId: Int, settingName: String): DownloadPayload? {
        return try{

            val setting = settingRepository.findByUserIdAndSettingName(userId,settingName) ?: throw Error("Setting Cannot be found")

            DownloadPayload(
                settingName = setting.settingName,
                instanceSetting = setting.settingString
            )
        }catch(e: Error){
            println("Error while downloading payload: " + e.message)
            null
        }
    }
}