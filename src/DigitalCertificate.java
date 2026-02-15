import java.io.*;
import java.security.*;
import java.util.Base64;
import java.util.Date;

public class DigitalCertificate implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String ownerName;
    private String publicKey;
    private String issuer;
    private Date validFrom;
    private Date validTo;
    private String signature;

    public DigitalCertificate(String ownerName, PublicKey pubKey, String issuer) {
        this.ownerName = ownerName;
        this.publicKey = Base64.getEncoder().encodeToString(pubKey.getEncoded());
        this.issuer = issuer;
        this.validFrom = new Date();
        this.validTo = new Date(System.currentTimeMillis() + (365L * 24 * 60 * 60 * 1000));
    }

    public byte[] getCertificateData() {
        return (ownerName + publicKey + issuer + validFrom.toString() + validTo.toString()).getBytes();
    }

    public void signCertificate(PrivateKey caPrivateKey) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initSign(caPrivateKey);
        sign.update(getCertificateData());
        byte[] signedBytes = sign.sign();
        signature = Base64.getEncoder().encodeToString(signedBytes);
    }

    public boolean verifyCertificate(PublicKey caPublicKey) throws Exception {
        Signature verify = Signature.getInstance("SHA256withRSA");
        verify.initVerify(caPublicKey);
        verify.update(getCertificateData());
        return verify.verify(Base64.getDecoder().decode(signature));
    }

    public void saveCertificate(String path) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(this);
        out.close();
    }
    public String getOwnerName() {
        return ownerName;
    }
    public String getIssuer() {
        return issuer;
    }

    public java.util.Date getValidFrom() {
        return validFrom;
    }

    public java.util.Date getValidTo() {
        return validTo;
    }

}
