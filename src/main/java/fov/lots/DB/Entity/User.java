package FoV.LoTs.DB.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.UUID;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Comment("Unique Id")
    private UUID id;

    @Column(unique = true, nullable = false)
    @Comment("User Email")
    private String email;
    
    @Column(nullable = false)
    @Comment("Password")
    private String password;

    @Column
    @Comment("isActived")
    private Boolean isActived;

    public String GetId()
    {
        return this.email;
    }

    public void SetId(String id)
    {
        this.email = id;
    }

    public String GetPassword()
    {
        return this.password;
    }

    public void SetPassword(String password)
    {
        this.password = password;
    }
}
