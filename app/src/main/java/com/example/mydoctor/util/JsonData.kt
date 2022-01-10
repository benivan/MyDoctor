package com.example.mydoctor.data

data class JsonData(
    val diseases: List<JsonDisease>,
)

data class JsonDisease(
    val name: String,
    val medications: List<JsonMedications>,
    val labs:List<JsonLabs>
)

data class JsonMedications(
    val name: String,
    val dose: String,
    val strength: String

)

data class JsonLabs(
    val test: String,
    val report: String
)
