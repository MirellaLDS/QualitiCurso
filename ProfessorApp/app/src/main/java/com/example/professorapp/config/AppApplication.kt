package com.example.professorapp.config

import android.app.Application
import com.example.professorapp.network.CourseNetwork
import com.example.professorapp.network.DepartamentNetwork
import com.example.professorapp.network.ProfessorNetwork
import com.example.professorapp.service.CourseService
import com.example.professorapp.service.DepartamentService
import com.example.professorapp.service.ProfessorService
import com.example.professorapp.view.curso.CursoViewModel
import com.example.professorapp.view.departament.DepartamentViewModel
import com.example.professorapp.view.professor.ProfessorViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startInjection()
    }

    private fun startInjection() {
        val moduleViewModel = module {
            factory { CursoViewModel(dispatcher = Dispatchers.IO, network = get()) }
            factory { DepartamentViewModel(dispatcher = Dispatchers.IO, network = get()) }
            factory { ProfessorViewModel(dispatcher = Dispatchers.IO, network = get()) }
        }

        val moduleNetwork = module {
            factory { CourseNetwork(get()) }
            factory { DepartamentNetwork(get()) }
            factory { ProfessorNetwork(get()) }
        }

        val moduleConfig = module {
            single { providerRetrofit() }
            factory { getCourseService(get()) }
            factory { getDepartamentService(get()) }
            factory { getProfessorService(get()) }
        }
        startKoin(this, listOf(moduleViewModel, moduleNetwork, moduleConfig))
    }

    fun getCourseService(retrofit: Retrofit): CourseService {
        return retrofit.create(CourseService::class.java)
    }

    fun getDepartamentService(retrofit: Retrofit): DepartamentService {
        return retrofit.create(DepartamentService::class.java)
    }

    fun getProfessorService(retrofit: Retrofit): ProfessorService {
        return retrofit.create(ProfessorService::class.java)
    }

    fun providerRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://professor-allocation.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}