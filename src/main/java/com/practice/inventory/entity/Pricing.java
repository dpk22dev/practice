package com.practice.inventory.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "Pricing")
@Getter
@Setter
public class Pricing {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Basic
  @Column(name = "price", nullable = false)
  private Long price;

  @Basic
  @Column(name = "currency", nullable = false, length = 3)
  private String currency;
}
