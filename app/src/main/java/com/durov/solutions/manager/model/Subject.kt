package com.durov.solutions.manager.model

data class Subject(
    val name: String,
    val factors: List<Factor>,
    val solutions: List<Solution>
)
