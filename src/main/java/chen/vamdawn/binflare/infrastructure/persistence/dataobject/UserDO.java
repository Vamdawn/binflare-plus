package chen.vamdawn.binflare.infrastructure.persistence.dataobject;

import lombok.Data;

import java.time.Instant;

/**
 * persistence: user
 *
 * @author chen
 */
@Data
public class UserDO {

    private Long id;

    private String userNo;

    private Byte status;

    private String nickname;

    private String avatar;

    private Instant createTime;

    private Instant updateTime;
}
