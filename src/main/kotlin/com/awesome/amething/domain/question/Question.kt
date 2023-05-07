package com.awesome.amething.domain.question

import com.awesome.amething.domain.user.User
import com.awesome.amething.global.enums.QuestionType
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "quesion")
class Question (user: User, title : String, questionType : QuestionType = QuestionType.UNPIN){


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    val id : Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    val user : User = user

    @Column(name = "question_title")
    var title : String = title

    @Enumerated(value = EnumType.STRING)
    @Column(name = "question_type")
    var questionType : QuestionType = questionType

}