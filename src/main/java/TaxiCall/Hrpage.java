package TaxiCall;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Hrpage_table")
public class Hrpage {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private Long driverId;
        private String driverStatus;
        private String driverNm;




        public String getDriverStatus() {
            return driverStatus;
        }

        public void setDriverStatus(String driverStatus) {
            this.driverStatus = driverStatus;
        }
        public String getDriverNm() {
            return driverNm;
        }

        public void setDriverNm(String driverNm) {
            this.driverNm = driverNm;
        }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}
