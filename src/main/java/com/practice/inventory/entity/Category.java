package com.practice.inventory.entity;

import java.util.List;

import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter
@Setter
public class Category {
  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categoryId;

  @Basic
  @Column(name = "title", nullable = false, length = 50)
  private String title;

  @Basic
  @Column(name = "description", nullable = true)
  private String description;

  @ManyToMany(mappedBy = "categories")
  private List<Product> product;

}
