package com.tenet.iot.system;

import com.baomidou.mybatisplus.extension.service.IService;

public interface MemberService extends IService<TtMember> {
    TtMember findByMemberId(String memberId);
}
