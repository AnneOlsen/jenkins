#!/bin/bash
sudo docker image build  . --tag springdbimage
sudo docker run -p 80:8080 --name  springdbcontainer springdbimage 
