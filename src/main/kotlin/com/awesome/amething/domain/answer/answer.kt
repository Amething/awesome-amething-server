package com.awesome.amething.domain.answer

import com.awesome.amething.domain.question.Question
import jakarta.persistence.*

@Entity
@Table(name = "answer")
class answer (question : Question, description : String){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    val id : Long? = null

    @OneToOne(fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "question_id")
    val question : Question = question

    @Column(name = "answer_description")
    var description : String = description
}