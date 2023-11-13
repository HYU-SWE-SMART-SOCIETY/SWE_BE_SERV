package com.holme.be_app.repository

import com.holme.be_app.entity.InstanceSetting
import org.springframework.data.repository.CrudRepository

interface SettingRepository: CrudRepository<InstanceSetting, Int> {

}