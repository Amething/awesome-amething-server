package com.awesome.amething.domain.answer

import com.awesome.amething.domain.question.Question
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "answer")
class Answer(
    @OneToOne(fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "question_id")
    val question: Question,

    @Column(name = "answer_description")
    var content: String,
) {
    init {
        question.addAnswer()
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private var _id: Long? = null

    val id get() = _id!!
}
