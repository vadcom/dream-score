@echo off
scp -i "C:\Users\PC\Keys\website_key.pem" target/dream-score-1.2.1.jar ec2-user@18.159.129.136:~/dreamscore/
ssh -i "C:\Users\PC\Keys\website_key.pem" ec2-user@18.159.129.136 "sudo systemctl restart dreamscore"
