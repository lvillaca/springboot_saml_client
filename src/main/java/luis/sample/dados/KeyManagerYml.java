package luis.sample.dados;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "key-manager")
public class KeyManagerYml {
        private String classpath;
        private String storePass;
        private String alias;
        private String signingAlgorithm;
        private boolean signMetadata;
        private String metadataUrl;
        private boolean metadataTrustCheck;
        private String entityId;
    
        public String getClasspath() {
            return classpath;
        }
    
        public String getStorePass() {
           return storePass;
        }
    
        public String getAlias() {
            return alias;
        }
    
        public String getSigningAlgorithm() { 
            return signingAlgorithm; 
        }
    
        public boolean isSignMetadata() { 
            return signMetadata;
        }
    
        public String getMetadataUrl() {
            return metadataUrl;
        }
    
        public boolean isMetadataTrustCheck() {
            return metadataTrustCheck;
        }
    
        public String getEntityId() {
            return entityId;
        }
    
        public void setClasspath(String cp) {
            classpath = cp;
        }
    
        public void setStorePass(String sp) {
            storePass = sp;
        }
    
        public void setAlias(String al) {
            alias = al;
        }

        public void setSigningAlgorithm(String signingAlgorithm) {
            this.signingAlgorithm = signingAlgorithm;
        }

        public void setSignMetadata(boolean signMetadata) {
            this.signMetadata = signMetadata;
        }

        public void setMetadataUrl(String metadataUrl) {
            this.metadataUrl = metadataUrl;
        }

        public void setMetadataTrustCheck(boolean metadataTrustCheck) {
            this.metadataTrustCheck = metadataTrustCheck;
        }

        public void setEntityId(String entityId) {
            this.entityId = entityId;
        }

}
