package com.shcl.smarthealth.presentation.view.survey

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.shcl.smarthealth.domain.model.remote.survey.CategoryQuestionResponse
import com.shcl.smarthealth.domain.model.remote.survey.Question
import com.shcl.smarthealth.domain.model.remote.survey.answer.Answer
import com.shcl.smarthealth.domain.model.remote.survey.answer.CategoryQuestionRequest
import com.shcl.smarthealth.domain.usecase.survey.SurveyUseCase
import com.shcl.smarthealth.domain.usecase.voice.VoiceUseCase
import com.shcl.smarthealth.presentation.view.survey.content.Level1Assistant
import com.shcl.smarthealth.presentation.view.survey.content.Level2Assistant
import com.shcl.smarthealth.presentation.view.survey.content.Level3Assistant
import com.shcl.smarthealth.presentation.view.survey.content.Level4Assistant
import com.shcl.smarthealth.presentation.view.survey.content.Level5Assistant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject


enum class SurveyByLevel(val title : String, val desc:String , val category:String){

    LEVEL1("식습관" , "식습관" , "EATING"),
    LEVEL2("수면습관","수면습관" , "SLEEPING"),
    LEVEL3("우울감","우울감", "DEPRESSION"),
    LEVEL4("흡연/음주","흡연 및 음주" , "SMOKING_AND_DRINKING"),
    LEVEL5("신체활동 / 질병력 및 가족력" , "평소 신체활동" , "ACTIVITY_AND_MEDICAL_HISTORY")
}




@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val surveyUseCase: SurveyUseCase,
    private val voiceUseCase: VoiceUseCase,
) : ViewModel() {

    val MAX_LEVEL = 5
    val MIN_LEVEL = 1

    private val _levelState = MutableStateFlow(1)
    val levelState = _levelState.asStateFlow()

    private val _levelTitleState = MutableStateFlow(SurveyByLevel.LEVEL1)
    val levelTitleState = _levelTitleState.asStateFlow()

    private val _validation = MutableStateFlow(false)
    val validation = _validation.asStateFlow()

    private var _level1HashMap : HashMap<Int,Answer> = HashMap<Int,Answer>()
    private var _level1Answers = MutableStateFlow(_level1HashMap)

    private var _level2HashMap : HashMap<Int,Answer> = HashMap<Int,Answer>()
    private var _level2Answers = MutableStateFlow(_level2HashMap)

    private var _level3HashMap : HashMap<Int,Answer> = HashMap<Int,Answer>()
    private var _level3Answers = MutableStateFlow(_level3HashMap)

    private var _level4HashMap : HashMap<Int,Answer> = HashMap<Int,Answer>()
    private var _level4Answers = MutableStateFlow(_level4HashMap)

    private var _level5HashMap : HashMap<Int,Answer> = HashMap<Int,Answer>()
    private var _level5Answers = MutableStateFlow(_level5HashMap)

    private var surveyId : Int = 1
    private var answerId : Int = 0


    private var _questions : List<Question>? = null
    var questions = MutableStateFlow(_questions)

    private var _uploadSuccess : Boolean = false
    var uploadSuccess = MutableStateFlow(_uploadSuccess)

    private var essentialMap = HashMap<Int , Boolean>()


    private val _surveyComplete = MutableStateFlow(false)
    val surveyComplete = _surveyComplete.asStateFlow()

    init {

        surveyInfo()

        /*
        for(key in 1..LEVEL1_QUESTION_CNT){
            _level1HashMap.put(key , null)
        }

        for(key in 1..LEVEL3_QUESTION_CNT){
            _level3HashMap.put(key , null)
        }

        for(key in 1..LEVEL4_QUESTION_CNT){
            _level4HashMap.put(key , null)
        }

        for(key in 1..LEVEL5_QUESTION_CNT){
            _level5HashMap.put(key , null)
        }

         */

    }

    fun next(){

        uploadSuccess.value = false

        if(_levelState.value + 1 >= MAX_LEVEL){
            _levelState.value = MAX_LEVEL
        }else{
            _levelState.value = _levelState.value + 1;
        }
        levelChange(_levelState.value)
    }

    fun addLevel1Answer(answer : Answer){
        _level1Answers.value.put(answer.questionId , answer)
        _validation.value = validationAnswer(_level1Answers.value)
        voiceAssistant(questionId = answer.questionId + 1)
    }

    fun addLevel2Answer(answer : Answer){
        _level2Answers.value.put(answer.questionId , answer)
        _validation.value = validationAnswer(_level2Answers.value)
        voiceAssistant(questionId = answer.questionId , answer)
    }

    fun addLevel3Answer(answer : Answer){
        _level3Answers.value.put(answer.questionId , answer)
        _validation.value = validationAnswer(_level3Answers.value)
        voiceAssistant(questionId = answer.questionId + 1)
    }

    fun addLevel4Answer(answer : Answer){
        _level4Answers.value.put(answer.questionId , answer)
        _validation.value = validationAnswer(_level4Answers.value)
        voiceAssistant(questionId = answer.questionId , answer)
    }

    fun addLevel5Answer(answer : Answer){
        _level5Answers.value.put(answer.questionId , answer)
        _validation.value = validationAnswer(_level5Answers.value)
        voiceAssistant(questionId = answer.questionId + 1)
    }


    fun levelChange(level : Int){

        essentialMap.clear()
        _validation.value = false

        when(level){
            1-> {
                _levelTitleState.value = SurveyByLevel.LEVEL1

                val list = Level1Assistant.getEssentialQuestionsList()

                list.forEach{ assistant->
                    essentialMap.put(assistant.questionId , false)
                }
            }
            2->{
                _levelTitleState.value = SurveyByLevel.LEVEL2

                val list = Level2Assistant.getEssentialQuestionsList()

                list.forEach{ assistant->
                    essentialMap.put(assistant.questionId , false)
                }
            }
            3-> {
                _levelTitleState.value = SurveyByLevel.LEVEL3

                val list = Level3Assistant.getEssentialQuestionsList()

                list.forEach{ assistant->
                    essentialMap.put(assistant.questionId , false)
                }
            }
            4-> {
                _levelTitleState.value = SurveyByLevel.LEVEL4

                val list = Level4Assistant.getEssentialQuestionsList()

                list.forEach{ assistant->
                    essentialMap.put(assistant.questionId , false)
                }
            }
            5-> {
                _levelTitleState.value = SurveyByLevel.LEVEL5

                val list = Level5Assistant.getEssentialQuestionsList()

                list.forEach{ assistant->
                    essentialMap.put(assistant.questionId , false)
                }
            }
        }

        getCategoryQuestion(_levelTitleState.value.category)
    }

    fun validationAnswer(answer : HashMap<Int , Answer>) : Boolean{

        answer.values.forEach { answer->
            essentialMap.replace(answer.questionId , true)
        }

        return !essentialMap.containsValue(false)
    }

    fun prev(){
        if(_levelState.value - 1 <= MIN_LEVEL){
            _levelState.value = MIN_LEVEL
        }else{
            _levelState.value = _levelState.value - 1
        }
        levelChange(_levelState.value)
    }

    fun surveyStart(){

        GlobalScope.launch(Dispatchers.IO) {
            surveyUseCase.startSurveyUseCase.invoke(surveyId)
                .onStart {   Log.d("smarthealth" , "survey start") }
                .onCompletion {  Log.d("smarthealth" , "") }
                .catch {   Log.d("smarthealth" , "")}
                .collect{
                    it.let {

                        if(it.success){
                            it.data?.let { data->
                                answerId = data.id
                            }
                        }

                        Log.d("smarthealth" , "survey  : ${it}")
                    }
                }
        }
    }

    fun getCategoryQuestion(category : String){

        GlobalScope.launch(Dispatchers.IO) {
            surveyUseCase.getCategoryQuestionUseCase.invoke(surveyId , category)
                .onStart {   Log.d("smarthealth" , "survey question") }
                .onCompletion {  Log.d("smarthealth" , "") }
                .catch {   Log.d("smarthealth" , "")}
                .collect{
                    it.let {
                        if(it.success){
                            questions.value = it.data!!
                            voiceAssistant(questions.value!!.first().questionId)
                        }
                        Log.d("smarthealth" , "survey  : ${it}")
                    }
                }
        }
    }

    fun surveyInfo(){
        GlobalScope.launch(Dispatchers.IO) {
            surveyUseCase.getSurveysInfoUseCase.invoke()
                .onStart {   Log.d("smarthealth" , "survey info") }
                .onCompletion {  Log.d("smarthealth" , "") }
                .catch {   Log.d("smarthealth" , "")}
                .collect{
                    it.let {

                        if(it.success){
                            surveyId = it.data?.id ?: 0
                            levelChange(1)
                           // getCategoryQuestion(SurveyByLevel.LEVEL1.category)
                            surveyStart()
                        }
                        Log.d("smarthealth" , "survey : ${it}")
                    }
                }
        }
    }

    fun surveyComplete(){

        GlobalScope.launch(Dispatchers.IO) {
            surveyUseCase.completeSurveyUseCase.invoke(answerId)
                .onStart {   Log.d("smarthealth" , "survey complete") }
                .onCompletion {  Log.d("smarthealth" , "") }
                .catch {   Log.d("smarthealth" , "")}
                .collect{
                    it.let {

                        if(it.success){
                            _surveyComplete.value = true
                        }

                        Log.d("smarthealth" , "survey : ${it}")
                    }
                }
        }

    }

    fun voiceAssistant(questionId: Int , answer : Answer? = null){

        var voice : String? = ""

        when(levelTitleState.value){
            SurveyByLevel.LEVEL1->{
                voice =  Level1Assistant.getVoiceByQuestionId(questionId)
            }
            SurveyByLevel.LEVEL2->{
                val assistant = Level2Assistant.getQuestion(questionId)

                answer?.let {
                    when(it.questionId){
                        12->{
                                if(it.answer.toString().compareTo("GOOD") == 0 || it.answer.toString().compareTo("VERY_GOOD") == 0){
                                    voice = Level2Assistant.getVoiceByQuestionId(assistant!!.defaultNextQuestionId)
                                }else{
                                    voice = Level2Assistant.getVoiceByQuestionId(assistant!!.jumpQuestionId)
                                }
                        }

                        24->{
                            if(it.answer == true){
                                voice = Level2Assistant.getVoiceByQuestionId(assistant!!.jumpQuestionId)
                            }else{
                                voice = Level2Assistant.getVoiceByQuestionId(assistant!!.defaultNextQuestionId)
                            }
                        }

                        else->{
                            voice = Level2Assistant.getVoiceByQuestionId(assistant!!.defaultNextQuestionId)
                        }
                    }
                } ?: run {
                    voice = Level2Assistant.getVoiceByQuestionId(questionId)
                }


            }
            SurveyByLevel.LEVEL3->{
                voice = Level3Assistant.getVoiceByQuestionId(questionId)
            }
            SurveyByLevel.LEVEL4->{

                val assistant = Level4Assistant.getQuestion(questionId)

                answer?.let {
                    when(it.questionId){
                        43->{
                            if(it.answer.toString().contains("NOT_NOW")){
                                voice = Level4Assistant.getVoiceByQuestionId(assistant!!.jumpQuestionId)
                            }else if(it.answer.toString().contains("NOW")){
                                voice = Level4Assistant.getVoiceByQuestionId(assistant!!.otherJumpQuestionId)
                            }else{
                                voice = Level4Assistant.getVoiceByQuestionId(assistant!!.defaultNextQuestionId)
                            }
                        }

                        48->{
                            if(it.answer.toString().compareTo("NOW") == 0){
                                voice = Level4Assistant.getVoiceByQuestionId(assistant!!.jumpQuestionId)
                            }else{
                                voice = Level4Assistant.getVoiceByQuestionId(assistant!!.defaultNextQuestionId)
                            }
                        }

                        else->{
                            voice = Level4Assistant.getVoiceByQuestionId(assistant!!.defaultNextQuestionId)
                        }
                    }
                } ?: run{
                    voice = Level4Assistant.getVoiceByQuestionId(questionId)
                }
            }
            SurveyByLevel.LEVEL5->{
                voice = Level5Assistant.getVoiceByQuestionId(questionId)
            }
        }

        voice?.let {
            if(!voice.isNullOrEmpty()){
                viewModelScope.launch {
                    voiceUseCase.voiceTTSUseCase.invoke(
                        spearker = "nara",
                        text = it)
                        .collect{
                            it?.let {
                                voiceUseCase.voicePlayUseCase.invoke(it).collect{

                                }
                            }
                        }
                }
            }
        }

    }

    fun uploadAnswer(){

        var answers : List<Answer>

        when(levelTitleState.value){
               SurveyByLevel.LEVEL1->{ answers = _level1HashMap.values.toList() }
                SurveyByLevel.LEVEL2->{ answers = _level2HashMap.values.toList() }
                SurveyByLevel.LEVEL3->{ answers = _level3HashMap.values.toList() }
                SurveyByLevel.LEVEL4->{ answers = _level4HashMap.values.toList()}
                SurveyByLevel.LEVEL5->{ answers = _level5HashMap.values.toList()}
        }


        var categoryQuestionRequest  = CategoryQuestionRequest(
            answerId = answerId,
            category = _levelTitleState.value.category,
            answers =  answers
        )

        GlobalScope.launch(Dispatchers.IO) {
            surveyUseCase.setCategoryAnswerUseCase.invoke(categoryQuestionRequest)
                .onStart {   Log.d("smarthealth" , "survey upload") }
                .onCompletion {  Log.d("smarthealth" , "") }
                .catch {   Log.d("smarthealth" , "")}
                .collect{
                    it.let {
                        if(it.success){
                            uploadSuccess.value  = true
                            Log.d("smarthealth" , "survey upload success")

                            if(_levelState.value == MAX_LEVEL){
                                surveyComplete()
                            }

                        }
                        Log.d("smarthealth" , "survey : ${it}")
                    }
                }
        }
    }
}