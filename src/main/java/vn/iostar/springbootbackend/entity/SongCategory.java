package vn.iostar.springbootbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "song_categories")
public class SongCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_song_category")
    private Long songCategoryId;

    @Column(name = "name", nullable = false, columnDefinition = "nvarchar(1000)")
    private String name;

    @OneToMany(mappedBy = "songCategory", cascade = CascadeType.ALL)
    private List<Song> songs;
}
