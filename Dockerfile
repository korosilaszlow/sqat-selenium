FROM ubuntu:22.04

# Install dependencies and enable universe repository
RUN apt-get update && \
    apt-get install -y curl unzip wget gnupg2 software-properties-common locales && \
    add-apt-repository universe

# Generate and set the locale to UTF-8
RUN locale-gen en_US.UTF-8 && \
    update-locale LANG=en_US.UTF-8

# Set environment variables for the locale
ENV LANG=en_US.UTF-8
ENV LANGUAGE=en_US:en
ENV LC_ALL=en_US.UTF-8

# Install Java 21
RUN apt-get update && \
    apt-get install -y openjdk-21-jdk

# Set environment variables for Java
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH=$PATH:$JAVA_HOME/bin

# Install Gradle 8.5
ENV GRADLE_VERSION=8.5
RUN wget -q https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip && \
    unzip gradle-$GRADLE_VERSION-bin.zip && \
    rm gradle-$GRADLE_VERSION-bin.zip && \
    mv gradle-$GRADLE_VERSION /opt/gradle && \
    ln -s /opt/gradle/bin/gradle /usr/bin/gradle

# Set environment variables for Gradle
ENV GRADLE_HOME=/opt/gradle

# Add Gradle to PATH
ENV PATH=$PATH:$GRADLE_HOME/bin

RUN apt-get update && apt-get install -y sudo

# Create a user
RUN username="selenium" && \
    addgroup -gid 1000 $username && \
    mkdir -p "/home/$username" && \
    cp -a /root/. "/home/$username" && \
    adduser --uid 1000 --home "/home/$username" --gid 1000 --quiet --disabled-password --gecos "Mr. $username User,,,"  $username && \
    usermod -p "Q4oQmhJG0ctkM" $username && \
    sudo usermod -a -G sudo $username && \
    chown -R "$username.$username" "/home/$username"

# Set timezone
ENV TZ=Europe/Budapest
ENV DEBIAN_FRONTEND=noninteractive
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Convert Java source files to UTF-8
RUN apt-get install -y dos2unix
WORKDIR /home/selenium/
RUN find /home/selenium -name "*.java" -exec bash -c 'iconv -f WINDOWS-1250 -t UTF-8 "$1" -o "${1%.java}.utf8.java" && mv "${1%.java}.utf8.java" "$1"' _ {} \;

# Set home directory
ENV HOME=/home/selenium/

USER selenium

CMD ["/bin/bash"]
