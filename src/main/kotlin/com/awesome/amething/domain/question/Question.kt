package com.awesome.amething.domain.question

import com.awesome.amething.domain.answer.Answer
import com.awesome.amething.domain.user.User
import com.awesome.amething.global.enums.QuestionType
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "quesion")
class Question(
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    val user: User = User(),

    @Column(name = "question_title")
    var title: String = "title",

    @Enumerated(value = EnumType.STRING)
    @Column(name = "question_type")
    var questionType: QuestionType = QuestionType.UNPIN,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "question")
    val answer: Answer? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private var _id: Long? = null

    val id: Long
        get() = _id!!
    fun addAnswer() {
        questionType = QuestionType.PIN
    }
}
