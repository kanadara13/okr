package member.service

import com.platform.database.DatabaseFactory
import com.platform.member.domain.NewMember
import com.platform.member.service.MemberSaver
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Test


class MemberSaverTest {

    @Test
    fun `test runBlocking`() {
        DatabaseFactory.init(isDev = true)
        runBlocking {
            for (i in 0..100){
                saveMembers(i)
            }
        }
    }

    @Test
    fun `use repeat`(){
        repeat(100) { i -> println(i)}
    }

    private suspend fun saveMembers(i: Int) {
        val saver = MemberSaver()
        saver.save(NewMember("email$i","name$i","password$i"))
    }
}