package com.rakamintest.chatapp.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "participant")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_one")
    private int userOne;

    @Column(name = "user_two")
    private int userTwo;

}
