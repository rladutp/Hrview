package TaxiCall;

import TaxiCall.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HrpageViewHandler {


    @Autowired
    private HrpageRepository hrpageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRegistered_then_CREATE_1 (@Payload Registered registered) {
        System.out.println("registered");
        try {
            if (registered.isMe()) {
                // view 객체 생성
                Hrpage hrpage = new Hrpage();
                // view 객체에 이벤트의 Value 를 set 함
                hrpage.setDriverId(registered.getDriverId());
                hrpage.setDriverStatus(registered.getDriverStatus());
                hrpage.setDriverNm(registered.getDriverNm());
                // view 레파지 토리에 save
                hrpageRepository.save(hrpage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenRetired_then_UPDATE_1(@Payload Retired retired) {
        System.out.println("retired");
        try {
            System.out.println("0");
            if (retired.isMe()) {
                System.out.println("id  :  "+retired.getDriverId());
                // view 객체 조회
                Optional<Hrpage> hrpageOptional = hrpageRepository.findById(retired.getDriverId());
                if( hrpageOptional.isPresent()) {
                    Hrpage hrpage = hrpageOptional.get();
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    hrpage.setDriverStatus(retired.getDriverStatus());
                    // view 레파지 토리에 save
                    hrpageRepository.save(hrpage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}