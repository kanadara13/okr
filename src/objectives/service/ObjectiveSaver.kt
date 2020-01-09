package com.platform.objectives.service

import com.platform.member.domain.NewMember
import com.platform.member.entity.Member
import com.platform.member.entity.MemberGroup
import com.platform.objectives.domain.NewObjectives
import com.platform.objectives.entity.Objectives
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class ObjectiveSaver {

    suspend fun save(new : NewObjectives) =  withContext(Dispatchers.IO) {
        transaction {
            val objectives = Objectives.new {
                this.groupNo   = new.groupNo
                this.title     = new.title
                this.createdAt = DateTime.now()
                this.updatedAt = DateTime.now()
            }
            objectives
        }
    }
}