package ggxnet.reload.service;

import ggxnet.reload.service.operation.Operation;
import ggxnet.reload.shared.CommandType;
import ggxnet.reload.shared.ParamType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LobbyService {
    private final Map<CommandType, Operation> operations;

    public void processPost(String request) {
        var paramService = new ParamService();
        Map<ParamType, String> params = paramService.calculate(request);
        var command = CommandType.valueOf(params.get(ParamType.CMD).toUpperCase());
        log.info("Actual command: {}", command);
        operations.get(command).process(params);
    }

    public void processGet(String request) {
    }
}