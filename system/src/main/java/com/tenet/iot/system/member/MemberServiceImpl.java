package com.tenet.iot.system.member;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tenet.iot.mapper.MemberMapper;
import com.tenet.iot.system.MemberService;
import com.tenet.iot.system.TtMember;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, TtMember> implements MemberService {
    @Override
    public TtMember findByMemberId(String memberId) {
        return listWrapper(wrapper -> wrapper.eq(TtMember::getMemberId, memberId)).stream().findFirst().orElse(null);
    }

    private List<TtMember> listWrapper(Consumer<LambdaQueryWrapper<TtMember>> consumer) {
        LambdaQueryWrapper<TtMember> wrapper = new LambdaQueryWrapper<>();
        if (consumer != null) {
            consumer.accept(wrapper);
        }
        return list(wrapper);
    }
}
